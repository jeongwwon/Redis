package SortedSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;

public class SortedSet {
    public static void main(String args[]){
        try(JedisPool jedisPool=new JedisPool("127.0.0.1",6379)){
            try(Jedis jedis=jedisPool.getResource()){
                var scores=new HashMap<String,Double>();
                scores.put("user1",100.0);
                scores.put("user2",30.0);
                scores.put("user3",50.0);
                scores.put("user4",80.0);
                scores.put("user5",15.0);

                jedis.zadd("game2:scores",scores);
                List<String> zrange = jedis.zrange("game2:scores", 0, Long.MAX_VALUE);
                zrange.forEach(System.out::println);

//                List<Tuple> tuples = jedis.zrangeWithScores("game2:scores", 0, Long.MAX_VALUE);
//                tuples.forEach(i-> System.out.println("%s %s".formatted(i.getElement(),i.getScore())));

                jedis.zincrby("game2:scores",100,"user5");
                List<Tuple> tuples = jedis.zrangeWithScores("game2:scores", 0, Long.MAX_VALUE);
                tuples.forEach(i-> System.out.println("%s %s".formatted(i.getElement(),i.getScore())));

            }
        }
    }
}
