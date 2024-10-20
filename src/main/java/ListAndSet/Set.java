package ListAndSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Set {
    public static void main(String args[]){
        try(JedisPool jedisPool = new JedisPool()){
            try(Jedis jedis = jedisPool.getResource()){
//                jedis.sadd("users:500:follow","100","200","300");
//                jedis.srem("users:500:follow","100");
//
//                java.util.Set<String> smembers = jedis.smembers("users:500:follow");
//                smembers.forEach(System.out::println);

//                System.out.println(jedis.sismember("users:500:follow","200"));
//                System.out.println(jedis.sismember("users:500:follow","120"));

                //s inter , scard
                System.out.println(jedis.scard("users:500:follow"));

                System.out.println(jedis.sinter("users:500:follow","users:100:follow"));
            }
        }
    }
}
