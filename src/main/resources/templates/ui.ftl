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

<#macro headerWithBootstrap title>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link href="/static/style/main.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
<body>
<div class="columns">
    <div class="column_main_wrapper">
    <div class="column_main">
    <H1 style="text-align: center">${title}</H1>
    <hr>
</#macro>