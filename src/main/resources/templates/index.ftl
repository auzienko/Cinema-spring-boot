<#import "ui.ftl" as ui/>
<@ui.header title="Welcome to Cinema Admin Panel"/>
<form action="/admin/signin" method="get">
    <div class="container">
        <button type="submit" class="btn">👤 SignIn</button>
    </div>
</form>
<form action="/admin/signup" method="get">
    <div class="container">
        <button type="submit" class="btn">👤 SignUp</button>
    </div>
</form>
<@ui.tail/>
