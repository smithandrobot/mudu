<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 2/3/11
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Mud U Leaderboard</title>
  <g:javascript>
    var params = { menu: false, scale:"noscale", quality:"high", allowScriptAccess:"always", wmode:"transparent", base:"/swf" };
    swfobject.embedSWF("${resource(dir: 'swf', file: 'mudu_chart.swf')}", "trophy", "520", "355", "10.0.0", "${resource(dir: 'swf', file: 'expressInstall.swf')}", {}, params);

    $(function() {
      $('#leaderlist').jScrollPane(
				{
					verticalDragMinHeight: 62,
					verticalDragMaxHeight: 62
				}
			);

			$('#this-weeks-list').jScrollPane(
				{
					verticalDragMinHeight: 62,
					verticalDragMaxHeight: 62
				}
			);
    });
  </g:javascript>
</head>
<body id="mudu">

<div id="header">
  <h1>
    Find It. Mud it. Share it.
  </h1><a href="#" title="All-Time Leaders">Mud Photo Now</a>
</div>
<div id="promo">
  <iframe src="blank.html" width="500" height="130" scrolling="no" id="hosted_promo"></iframe>
</div>
<div id="leaderboard-tabs">
  <a href="#" class="tab-all">All-Time Leaders</a>
  <a href="#" class="tab-week">This Week's Leaders</a>
</div>
<div id="leaderboard">
  <div>
    <table id="mudlisthead">
      <tr>
        <th class="col-num">
          &nbsp;
        </th>
        <th class="col-head-spacer" colspan="2">
          Mudder
        </th>
        <th class="col-creature">
          Weapon<br/>
          of Choice
        </th>
        <th class="col-props">
          Mud<br/>
          Props
        </th>
      </tr>
    </table>
  </div>
  <div id="leaderlist">
    <table id="mudlist">
      <tbody>
      <g:set var="counter" value="${1}" />
     <g:each in="${topTen}" var="rank">
        <tr>
        <td class="col-num">
          ${counter}.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/${rank.player.facebookId}/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          ${rank.player.name}
        </td>
        <td class="col-creature ${rank.favoriteWeapon.machine_name}" title="${rank.favoriteWeapon.name}">
          ${rank.favoriteWeapon.name}
        </td>
        <td class="col-props">
          ${rank.score}
        </td>
      </tr>
      <g:set var="counter" value="${counter + 1}" />
      </g:each>
      <!--<tr>
        <td class="col-num">
          1.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/712034/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Mark Phillip
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          2.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7903980/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Erin Young
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          3.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7905541/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Anthony 'Ants' Nguyen
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          4.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7946858/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Chase Quarterman
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          5.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/9207274/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Courtney Grimsley
        </td>
        <td class="col-creature sasplotch" title="Sasplotch">
          Sasplotch
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          6.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/16700013/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Leslie Brockman Chunta
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          7.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/16709522/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Amelia 'Doty' Bowie
        </td>
        <td class="col-creature buck" title="Buck Wild">
          Buck Wild
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          8.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/29604909/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Andrew Hogan
        </td>
        <td class="col-creature walnuts" title="Dr. Walnuts">
          Dr. Walnuts
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          9.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/712034/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Mark Phillip
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          10.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7903980/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Erin Young
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          11.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7905541/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Anthony 'Ants' Nguyen
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          12.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/7946858/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Chase Quarterman
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          13.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/9207274/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Courtney Grimsley
        </td>
        <td class="col-creature sasplotch" title="Sasplotch">
          Sasplotch
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          14.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/16700013/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Leslie Brockman Chunta
        </td>
        <td class="col-creature hairy" title="Dirty Hairy">
          Dirty Hairy
        </td>
        <td class="col-props">
          524
        </td>
      </tr>
      <tr>
        <td class="col-num">
          16.
        </td>
        <td class="col-pic">
          <img src="http://graph.facebook.com/16709522/picture" width="50" height="50" alt=""/>
        </td>
        <td class="col-name">
          Amelia 'Doty' Bowie
        </td>
        <td class="col-creature buck" title="Buck Wild">
          Buck Wild
        </td>
        <td class="col-props">
          524
        </td>
      </tr> -->
      </tbody>
    </table>
  </div>
</div>
<div id="trophy"></div>
</body>
</html>