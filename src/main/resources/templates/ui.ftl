<#macro header title>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="/static/style/main.css" rel="stylesheet">
</head>
<body>
<div class="columns">
    <div class="column_main_wrapper">
        <div class="column_main">
            <H1 style="text-align: center">${title}</H1>
            <hr>
            </#macro>

            <#macro tail>
        </div>
    </div>
    <div class="column_left"></div>
    <div class="column_right"></div>
</div>
</body>
</html>
</#macro>

<#macro headerWithjQuery title>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="/static/style/main.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="columns">
    <div class="column_main_wrapper">
        <div class="column_main">
            <H1 style="text-align: center">${title}</H1>
            <hr>
            </#macro>
