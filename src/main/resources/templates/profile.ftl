<#import "ui.ftl" as ui/>
<@ui.header title="Welcome to Cinema Admin Panel"/>
Welcome to your profile:
<#if user?has_content>
    <p style="text-align: center"><b>${user.username}</b></p>
    <p style="text-align: center"><b>${user.email}</b></p>
</#if>
<@ui.tail/>