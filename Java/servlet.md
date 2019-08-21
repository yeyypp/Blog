# Servlet

- Life Cycle

1. Load Servlet Class.
2. Create Instance of Servlet.
3. Call the servlets init() method.
4. Call the servlets service() method.
5. Call the servlets destroy() method.

- 是否单例

是单例的，需要根据具体实现判断是否线程安全