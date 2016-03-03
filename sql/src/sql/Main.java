package sql;

import org.apache.commons.lang3.RandomStringUtils;
import org.h2.tools.DeleteDbFiles;

import java.sql.*;
import java.util.Random;


/*
* Create simple database with tables
* Users (id, name, surname, birthdate),
* Friendships (userid1, userid2, timestamp),
* Posts(id, userId, text, timestamp),
* Likes (postid, userid, timestamp).
* Fill tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2015).
* You program should print out all names (only distinct)
* of users who has more when 100 friends and 100 likes in March 2015.
* All actions (table creation, insert data and reading) should be implemented with JDBC.
* */
public class Main {

    private static final int USERS_COUNT = 1001;
    private static final int FRIENDSHIPS_COUNT = 90001;
    private static final int POSTS_COUNT = 3000;
    private static final int LIKES_COUNT = 300001;


    public static void main(String... args) throws Exception {
        // delete the database named 'test' in the user home directory
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");

        initializeAndFillUsersTable(USERS_COUNT);
        initializeAndFillFriendshipTable(USERS_COUNT, FRIENDSHIPS_COUNT);
        initializeAndFillPostsTable(USERS_COUNT, POSTS_COUNT);
        initializeAndFillLikesTable(USERS_COUNT, POSTS_COUNT, LIKES_COUNT);

        Statement stat = conn.createStatement();
        ResultSet rs;

        String query = "SELECT name FROM users WHERE id IN" +
                "(SELECT r1.userid1 FROM " +
                        "(SELECT userid1 FROM (SELECT userid1, count(*) AS frcount FROM friendship GROUP BY userid1) WHERE frcount > 100) r1 " +
                            "INNER JOIN " +
                        "(SELECT userid FROM posts WHERE id IN (SELECT postid FROM (SELECT postid, count(*) AS lcount FROM (SELECT * FROM likes WHERE timestamp < '2015-04-01') GROUP BY postid) WHERE lcount > 100)) r2 " +
                            "ON r1.userid1 = r2.userid)";

        rs = stat.executeQuery(query);
        int cnt = 0;
        System.out.println("\nUSERS : \n");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
            cnt++;
        }
    }

    private static void initializeAndFillLikesTable(int usersCount, int postCount, int likesCount) {
        Connection conn = null;
        Statement stat = null;
        PreparedStatement preparedStatement = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            conn.setAutoCommit(false);
            stat = conn.createStatement();

            stat.execute("create table likes(postid int, userid int, timestamp date)");
            stat.execute("alter table likes add foreign key (postid) references posts(id)");
            stat.execute("alter table likes add foreign key (userid) references users(id)");
            String insertLike = "insert into likes(postid, userid, timestamp) values (?, ?, ?)";

            long start = System.currentTimeMillis();
            Random random = new Random();
            int batchSize = 8192;
            long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
            long endd = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
            long diff = endd - offset + 1;
            Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
            for (int j = 0; j < likesCount/ batchSize; j++) {
                preparedStatement = conn.prepareStatement(insertLike);
                for (int i = 0; i < batchSize; i++) {
                    preparedStatement.setInt(1, random(1, postCount));
                    preparedStatement.setInt(2, random(1, usersCount));
                    preparedStatement.setDate(3, new Date(offset + (long)(Math.random() * diff)) );//new java.sql.Date(2010+random.nextInt(6), random(1,12), random(1,30)));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                preparedStatement.close();
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("Created " + likesCount + " likes for " + (end - start) + " mills");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void initializeAndFillPostsTable(int usersCount, int postCount) {
        Connection conn = null;
        Statement stat = null;
        PreparedStatement preparedStatement = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            stat = conn.createStatement();

            stat.execute("create table posts(id int auto_increment primary key, userid int, text varchar(255), timestamp date)");
            stat.execute("alter table posts add foreign key (userid) references users(id)");
            String insertPost = "insert into posts(userid, text, timestamp) values (?, ?, ?)";

            preparedStatement = conn.prepareStatement(insertPost);
            long start = System.currentTimeMillis();
            Random random = new Random();

            for (int i = 0; i < postCount; i++) {
                preparedStatement.setInt(1, random(1, usersCount));
                preparedStatement.setString(2, RandomStringUtils.randomAlphabetic(250));
                preparedStatement.setDate(3, new java.sql.Date(random(12, 16), random(1,12), random(1,30)));
                preparedStatement.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("Created " + postCount + " posts for " + (end - start) + " mills");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static java.sql.Date generateRandomDate(){
        int day = random(1, 30);
        int month = random(1, 12);
        int year = random(2012, 2016);
        return java.sql.Date.valueOf(year + "-" + month + "-" + day);
    }

    public static int random(int min, int max) {
        return (min + (int) Math.round(Math.random() * (max - min)));
    }

    private static void initializeAndFillFriendshipTable(int usersCount, int friendshipsCount) {
        Connection conn = null;
        Statement stat = null;
        PreparedStatement preparedStatement = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            stat = conn.createStatement();

            stat.execute("create table friendship(userid1 int, userid2 int, timestamp date)");
            stat.execute("alter table friendship add foreign key (userid1) references users(id)");
            stat.execute("alter table friendship add foreign key (userid2) references users(id)");
            String insertFriendship = "insert into friendship(userid1, userid2, timestamp) values (?, ?, ?)";

            preparedStatement = conn.prepareStatement(insertFriendship);
            long start = System.currentTimeMillis();
            Random random = new Random();
            for (int i = 0; i < friendshipsCount; i++) {
                preparedStatement.setInt(1, random(1, usersCount));
                preparedStatement.setInt(2, random(1, usersCount));
                preparedStatement.setDate(3, new java.sql.Date(random(2012, 2016), random(1,12), random(1,30)));
                preparedStatement.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("Created " + friendshipsCount + " friendships for " + (end - start) + " mills");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void initializeAndFillUsersTable(int usersCount) {
        Connection conn = null;
        Statement stat = null;
        PreparedStatement preparedStatement = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            stat = conn.createStatement();
            stat.execute("create table users(id int auto_increment primary key, name varchar(255), surname varchar(255), birthdate date)");

            String insertUsers = "insert into users(name, surname, birthdate) values (?, ?, ?)";

            preparedStatement = conn.prepareStatement(insertUsers);
            long start = System.currentTimeMillis();
            for (int i = 0; i < usersCount; i++) {
                preparedStatement.setString(1, RandomStringUtils.randomAlphabetic(20));
                preparedStatement.setString(2, RandomStringUtils.randomAlphabetic(20));
                preparedStatement.setDate(3,  new java.sql.Date(random(1970, 2000), random(1,12), random(1,30)));
                preparedStatement.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("Created " + usersCount + " users for " + (end - start) + " mills");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}