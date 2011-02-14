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
    var flashvars = {mudpie:${weapons.dirty_hairy}, buck_wild:${weapons.buck_wild}, squirrel:${weapons.dr_walnuts}, sasplotch:${weapons.sasplotch}}
    swfobject.embedSWF("${resource(dir: 'swf', file: 'mudu_chart.swf')}", "trophy", "520", "355", "10.0.0", "${resource(dir: 'swf', file: 'expressInstall.swf')}", flashvars, params);

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
<body id="mud_u">
<!-- <img src="http://twitpic.com/show/large/1fdn1r.jpg" /> -->
<div id="header">
  <h1>
    Find It. Mud it. Share it.
  </h1><a href="#" title="All-Time Leaders">Mud Photo Now</a>
</div>
<div id="promo">
  <iframe src="blank.html" width="500" height="130" scrolling="no" id="hosted_promo"></iframe>
</div>
<div id="leaderboard-tabs">
  <a href="#alltime" id="allTimeLeadersTab" class="tab" title="All Time Leaders" name="allTimeLeadersTab">All-Time Leaders</a> <a href="#thisweek" id="thisWeeksLeadersTab" class="tab" title="This Week's Leaders" name="thisWeeksLeadersTab">This Week's Leaders</a>
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
  <div id="tables-outer-container">
    <div id="tables-container">
      <div id="leaderlist">
        <table class="mudlist">
          <tbody>
          <g:set var="counter" value="${1}"/>
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
              <td title="${rank.favoriteWeapon.name}">
                <div class="col-creature ${rank.favoriteWeapon.machine_name}" >
                       ${rank.favoriteWeapon.name}
                </div>

              </td>
              <td class="col-props">
                <div class="splat">
                ${rank.score}
              </div>

              </td>
            </tr>
            <g:set var="counter" value="${counter + 1}"/>
          </g:each>

          </tbody>
        </table>
      </div><!--  -->
      <div id="this-weeks-list">
        <table class="mudlist">
          <tbody>
          <g:set var="counter" value="${1}"/>
          <g:each in="${weeklyTopTen}" var="rank">
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
              <td title="${rank.favoriteWeapon.name}">
                <div class="col-creature ${rank.favoriteWeapon.machine_name}" >
                       ${rank.favoriteWeapon.name}
                </div>

              </td>
              <td class="col-props">
                <div class="splat">
                ${rank.score}
              </div>

              </td>
            </tr>
            <g:set var="counter" value="${counter + 1}"/>
          </g:each>
          </tbody>
        </table>
      </div><!--  -->
    </div>
  </div>
</div>
<div id="trophy"></div>
<script type="text/javascript">
  var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
  document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
  try {
    var pageTracker = _gat._getTracker("UA-9835910-1");
    pageTracker._trackPageview();
  } catch(err) {
  }</script>
</body>
</html>