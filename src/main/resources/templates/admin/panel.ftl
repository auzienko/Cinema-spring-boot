<#import "/spring.ftl" as spring />
<#import "../ui.ftl" as ui/>
<#assign titletext><@spring.message 'panel.title'/></#assign>
<@ui.headerWithjQuery title="🌅 ${titletext}"/>
<#import "../ui.ftl" as ui/>
<form action="/admin/panel/films" method="get">
    <div class="container">
        <button type="submit" class="btn"><@spring.message 'panel.films'/></button>
    </div>
</form>
<form action="/admin/panel/halls" method="get">
    <div class="container">
        <button type="submit" class="btn"><@spring.message 'panel.halls'/></button>
    </div>
</form>
<form action="/admin/panel/sessions" method="get">
    <div class="container">
        <button type="submit" class="btn"><@spring.message 'panel.sessions'/></button>
    </div>
</form>
<@ui.tail/>