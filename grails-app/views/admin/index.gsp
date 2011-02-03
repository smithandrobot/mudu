<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 1/13/11
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name='layout' content='admin' />
    <title>Mud U Admin</title>
  </head>
  <body>
  <div id="pageBody">
            <h1>Mud U Admin</h1>

            <div id="controllerList" class="dialog">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                    </g:each>
                </ul>
            </div>
        </div>
  </body>
</html>