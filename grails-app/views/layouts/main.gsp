<!DOCTYPE html>
<html>
<head>
  <title><g:layoutTitle default="Mud U"/></title>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content="Mud U, Mud, Mudding, Jeep"/>
  <meta name="description" content="Find it. Mudit. Share it."/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

  <link rel="stylesheet" href="${resource(dir: 'css', file: 'reset.css', absolute: true)}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.jscrollpane.css', absolute: true)}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'mud_styles.css', absolute: true)}"/>

  <g:javascript src="jquery-1.4.2.min.js"/>
  <g:javascript src="swfobject.js"/>
  <g:javascript src="jquery.mousewheel.js"/>
  <g:javascript src="jquery.jscrollpane.min.js"/>

  <g:javascript>
    var params = { menu: false, scale:"noscale", quality:"high", allowScriptAccess:"always", wmode:"transparent", base:"/swf" };
    swfobject.embedSWF("${resource(dir: 'swf', file: 'mudu_chart.swf')}", "trophy", "520", "355", "10.0.0", "${resource(dir: 'swf', file: 'expressInstall.swf')}", {}, params);

    $(function() {
      $('#leaderlist').jScrollPane(
      {
        verticalDragMinHeight: 62,
        verticalDragMaxHeight: 62,
      }
      );
    });
  </g:javascript>

  <!--[if IE 6]>
  <g:javascript src="pngfix.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ie6.css', absolute: true)}" />
		<script type="text/javascript" charset="utf-8">
				DD_belatedPNG.fix('.transpng, .transbg, #subnav a, .cta a');
		</script>
  <![endif]-->

  <g:layoutHead/>

</head>
<body>
<g:layoutBody/>
</body>
</html>