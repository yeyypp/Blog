public class Main {
    /**
     * Beans and DI
     * springboot中推荐使用@ComponentScan （find beans） @Autowired,当出现类型一致的对象时可以用@Qualifer缩小
     * 当application class 在root包下时，ComponentScan会自动扫描整个包
     *
     * @SpringBootApplication等于使用了 @Configuration, @EnableAutoConfiguration, and @ComponentScan
     */
}