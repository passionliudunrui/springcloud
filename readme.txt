2022.6.28
    完成了服务之间的调用
    通过直接RestTemplate 直接调用服务的HTTP请求。
    然后服务接受到HTTP请求后返回数据

    小tips：
    前端传入参数  使用{id} @PathVariable Integer id 来完成

2022.6.28
    实现了Eureka的部署调试

2022.6.29
    实现了Eureka的高可用和生产者的集群

2022.6.29
    Nginx和ribbon
    (1)nginx 是客户端所有请求统一交给 nginx，由 nginx 进行实现负载均衡请求转发，属于服务器端负载均衡。既请求由 nginx 服务器端进行转发。
    (2)Ribbon 是从 eureka 注册中心服务器端上获取服务注册信息列表，缓存到本地，然后在本地实现轮询负载均衡策略。既在客户端实现负载均衡。
    应用场景的区别：
    (1)Nginx适合于服务器端实现负载均衡比如 Tomcat ，Ribbon适合与在微服务中RPC远程调用实现本地服务负载均衡，比如 Dubbo、SpringCloud 中都是采用本地负载均衡。
    (2)spring cloud的Netflix中提供了两个组件实现软负载均衡调用：ribbon和feign。

    Ribbon是一个基于 HTTP 和 TCP 客户端的负载均衡器
    它可以在客户端配置 ribbonServerList（服务端列表），然后轮询请求以实现均衡负载。
    springcloud的ribbon和nginx有什么区别？哪个性能好？
    nginx性能好，但ribbon可以剔除不健康节点，nginx剔除节点比较复杂。ribbon还可以配合熔断器一起工作
    ribbon是客户端负载均衡，nginx是服务端负载均衡。客户端负载均衡，所有客户端节点都维护自己要访问的服务端清单。服务端负载均衡的软件模块会维护一个可用的服务清单
    ribbon 是一个客户端负载均衡器，可以简单的理解成类似于 nginx的负载均衡模块的功能。
    Ribbon就是实现服务端的负载均衡加RestTemplate的调用

    Ribbon的工作的流程：
        (1)先选择EurekaServer，它优先选择在同一个区域内负载较少的Server。
        (2)再根据用户指定的策略，在从Server取到服务注册列表中选择一个地址。
        其中ribbon提供了多种策略，比如轮询、随机、根据时间响应时间加权等。

2022.6.30
    整合Feign
    Feign集成了Ribbon，利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与Ribbon不同的是，
    通过Feign值需要定义服务绑定接口且一声明式的方法，优雅而简单的实现了服务调用。

    整合openFeign
    原因就是Openfeign默认等待1秒钟，超过后会报错。但是我们的生产者服务确实需要处理复杂的业务，处理时间会超过1秒，就需要修改Openfeign默认等待的时间。
    需要在消费者的服务的yml文件进行设置。因为Feign集成了Ribbon，所以需要设置Ribbon的相关。