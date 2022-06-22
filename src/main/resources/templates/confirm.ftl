<#import "ui.ftl" as ui/>
<@ui.header title="Email confirmation"/>
Welcome to your profile:
<#if user?has_content>
    <p style="text-align: center"><b>${user.username}</b></p>
    <p style="text-align: center"><b>${user.email}</b></p>
</#if>
<@ui.tail/>