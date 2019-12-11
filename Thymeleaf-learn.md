### Thymeleaf学习笔记

#### Thymeleaf标准方言：https://www.yiibai.com/thymeleaf/standard-dialects.html
   - 标准表达式语法
     - ${...}：变量表达式
     ```thymeleafexpressions
         <span th:text="${session.user.name}">
        
         # 循环读取books集合的内容
         <li th:each="book: ${books}">
     ```
     - *{...}：选择表达式
     ```thymeleafexpressions
     # 指向book对象的title属性
         <div th:object="${book}">
            ...
            <span th:text="*{title}">
            ...
         </div>
     ```
     - #{...}：消息（i18n）表达式
     ```thymeleafexpressions
         # 从properties中检索消息
         <th th:text="#{header.address.city}">...</th>
     ```
     - @{...}：链接表达式
     ```thymeleafexpressions
         <a th:href="@{alarm/getAlarmByName}">...</a>
         # 或者直接写成如下形式
         <a href="/alarm/getAlarmByName">...</a>
         # 保持会话，并且cookie未启用
         <a href="/myapp/order/list;jsessionid=s2ds3fa31abd241e2a01932">...</a>
         # 带参数如下：
         <a th:href="@{/alarm/{id}/getAlarmByName(id=3,name=${requestName})}">
     ```
     - ~{...}：片段表达式
     ```thymeleafexpressions
     ```
     
   - 文字操作
     - 文字
       - 文本文字：'One text'
       - 数字文字: 0, 11
       - 布尔文字: false, true
       - Null文字:Null
       - 文本标记: one
     - 文本操作
       - 字符串连接： +
       - 文字替换： |The name is ${name}|
     - 算术运算
       - 二进制操作： + 0 * / %
       - 减号（一元运算符）：-
     - 布尔运算
       - and or ！ not
     - 比较：
       - > < >= <= (gt, lt, ge, le),  == != (eq, ne)
     - 条件操作符
       - if-then： (if) ? (then)
       - if-then-else: (if) ? (then) : (else)
       - Default: (value) ?: (defaultValue)
   
   - 表达式预处理
   
#### Thymeleaf标准URL语法：https://www.yiibai.com/thymeleaf/standardurlsyntax.html#article-start
   - 