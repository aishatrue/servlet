<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import = "hello.servlet.domain.member.Member" %>
<%@ page import = "hello.servlet.domain.member.MemoryMemberRepository" %>



<%

        MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
%>
<html>
<head>

    <title>Title</title>
</head>
<body>
성공
<ul>
<li> id = <%=member.getId() %></li>
<li> username = <%=member.getName() %></li>
<li> age = <%=member.getAge() %></li>

</ul>
<a href="/index.html">메인</a>


</body>



</html>