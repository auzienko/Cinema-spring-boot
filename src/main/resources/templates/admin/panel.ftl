<#import "../ui.ftl" as ui/>
<@ui.header title="⚙️ Cinema Admin Panel"/>
<form action="/admin/panel/films" method="get">
    <div class="container">
        <button type="submit" class="btn">🎞 Films panel</button>
    </div>
</form>
<form action="/admin/panel/halls" method="get">
    <div class="container">
        <button type="submit" class="btn">🍿 Halls panel</button>
    </div>
</form>
<form action="/admin/panel/sessions" method="get">
    <div class="container">
        <button type="submit" class="btn">🌅 Sessions panel</button>
    </div>
</form>
<@ui.tail/>