package ListAndSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class Queue {
    public static void main(String args[]){
        try(JedisPool jedisPool = new JedisPool("127.0.0.1",6379)){
            try(Jedis jedis = jedisPool.getResource()){
                jedis.rpush("queue1","zzzz");
                jedis.rpush("queue1","aaaa");
                jedis.rpush("queue1","cccc");

                System.out.println(jedis.lpop("queue1"));
                System.out.println(jedis.lpop("queue1"));
                System.out.println(jedis.lpop("queue1"));

                // block brpop , blpop
                List<String> blpop = jedis.blpop(10, "queue:blocking");
                if(blpop!=null){
                    blpop.forEach(System.out::println);
                }

            }

        }
    }
}
