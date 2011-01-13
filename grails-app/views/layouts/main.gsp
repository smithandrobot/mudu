<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css',absolute:true)}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico',absolute:true)}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif',absolute:true)}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <h1>Mud U App</h1>
        <g:layoutBody />
    </body>
</html>