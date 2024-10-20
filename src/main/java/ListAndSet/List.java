package ListAndSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class List {
    public static void main(String[] args) {
        try(JedisPool jedisPool =new JedisPool("127.0.0.1",6379)){
            try(Jedis jedis=jedisPool.getResource()){
                jedis.rpush("stack1","aaaa");
                jedis.rpush("stack1","bbbb");
                jedis.rpush("stack1","ccc");
                java.util.List<String> stack1 = jedis.lrange("stack1", 0, -1);
                //stack1.forEach(System.out::println);
                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));
            }
        }
    }

}
