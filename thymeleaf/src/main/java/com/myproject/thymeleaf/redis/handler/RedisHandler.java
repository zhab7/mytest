package com.myproject.thymeleaf.redis.handler;

import com.myproject.thymeleaf.algorithm.Question;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanjianjian
 * @since 2021/5/7
 */
@Component
public class RedisHandler {

    public static final String SCORE_KEY = "score_key";
    public static final String TIME_KEY = "TIME_KEY";
    public static final Double SCORE = 432d;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void test() {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory == null) {
            throw new RuntimeException("连接报错");
        }
        connectionFactory.setDatabase(2);
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.opsForValue().set("key1", "sss", 10L, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().add("zsetkey", "sss", 15);

//        redisTemplate.opsForZSet()
    }

    public void vote(String bookId) {
        Double score = redisTemplate.opsForZSet().incrementScore(RedisHandler.SCORE_KEY, bookId, RedisHandler.SCORE);
        System.out.println("score = " + score);
    }

    public void selectVote() {
//        Set<Object> objects = redisTemplate.opsForZSet().range(RedisHandler.SCORE_KEY, 0, -1);
        // 按照分数倒序取出zset中的数据
        Set<Object> objects = redisTemplate.opsForZSet().reverseRange(RedisHandler.SCORE_KEY, 0, -1);
        if (objects != null) {
            for (Object object : objects) {
                System.out.println("object = " + object.toString());
            }
        }
    }


    public static void main(String[] args) {
/*        List<String> list = Lists.newArrayList("AAA", "BBB", "CCC", "DDD", "sdasdas", "sdferee", "sdregfds");
//        list.stream()
//        Optional<String> optional = new Optional<>();
        Optional<String> optional = list.stream().max(String::compareTo);
        String s = optional.orElse("");
        optional.ifPresent(list::add);

        System.out.println("s = " + s);
        System.out.println("list = " + list);
        int a = 0;
        Optional<Integer> integer = a != 0 ? Optional.empty() : Optional.of(1 + a);

        System.out.println("integer.get() = " + integer.get());

//        Optional<Object> o = Optional.of(null);
//        System.out.println("o = " + o.get());
//
//        Map<Integer, List<String>> collect = list.stream().collect(Collectors.groupingBy(String::length));
//        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
//        IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6});

        InputStream in = System.in;
        try {
            int available = in.available();
            if (available > 0) {
                int read = in.read(new byte[available]);
                System.out.println("read = " + read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        int[] arr = new int[]{2, 3, 5, 4, 3, 2, 6, 7};
        /*int[] num = getNums(arr);
        if (num != null && num.length > 0) {
            for (int i = 0; i < num.length; i++) {
                System.out.println("num = " + num[i]);
            }
        }*/
/*        int num = getNum(arr);
        System.out.println("num = " + num);*/

        int[] a = new int[]{1, 2, 8, 9};
        int[] b = new int[]{2, 4, 9, 12};
        int[] c = new int[]{4, 7, 10, 13};
        int[] d = new int[]{6, 8, 11, 15};
        int[][] arrs = new int[][]{a, b, c, d};

        boolean incloud = Question.includeNum(15, arrs);
        System.out.println("incloud = " + incloud);
    }


    public static int[] getNums(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    System.out.println("arr[i] = " + arr[i]);
//                    return null;
                } else {
                    int temp = arr[i];
                    arr[i] = arr[temp];
                    arr[temp] = temp;
                }
            }
        }
        return null;
    }

    public static int getNum(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while (end >= start) {
            int middle = ((end - start) >> 1) + start;
            int test = (end - start) / 2 + start;
            System.out.println("test = " + test + " middle = " + middle);
            int count = numTest(arr, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > middle - start + 1) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int numTest(int[] arr, int start, int end) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= end) {
                ++count;
            }
        }
        return count;
    }


}
