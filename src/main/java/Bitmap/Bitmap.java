package Bitmap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

//binary 데이터 타입으로,적은 메모리 사용으로 인한 효과적인 데이터 처리가 장점
//단순 집계 목적이라면 bitmap을 통해 아래 예시와 같이 400배 메모리 절약 효과가 나타난다.
public class Bitmap {
    public static void main(String args[]) {
        try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.setbit("request-somepage-20230305", 100, true);
                jedis.setbit("request-somepage-20230305", 200, true);
                jedis.setbit("request-somepage-20230305", 300, true);

                System.out.println(jedis.getbit("request-somepage-20230305", 100));
                System.out.println(jedis.getbit("request-somepage-20230305", 50));

                System.out.println(jedis.bitcount("request-somepage-20230305"));

                // bitmap vs set
                Pipeline pipelined = jedis.pipelined();
                IntStream.rangeClosed(0, 100000).forEach(i -> {
                    pipelined.sadd("request-somepage-set-20230306", String.valueOf(i), "1");
                    pipelined.setbit("request-somepage-bit-20230306", i, true);

                    if (i == 1000) {
                        pipelined.sync();
                    }
                });
                pipelined.sync();
            }
        }
    }
}
