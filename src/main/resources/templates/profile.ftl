<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'profile.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext}"/>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<table style="height: 288px; width: 60%; border-collapse: collapse; margin: 0px auto; ;" border="0" align="center">
    <tbody>
    <tr style="height: 270px;">
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">
                        <img src="${avatar}" width="150"/>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <form action="profile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data"
                              method="post">
                            <input type="file" name="avatarFile" class="btn" accept="image/*" required>
                            <button type="submit" class="btn"><@spring.message 'profile.upload'/></button>
                        </form>
                        <form action="/sessions" method="get">
                            <div class="container">
                                <button style="width: 350px" type="submit" class="btn" ><@spring.message 'profile.findSess'/></button>
                            </div>
                        </form>
                        <#if user.role = "ADMIN">
                            <form action="/admin/panel" method="get">
                                <div class="container">
                                    <button style="width: 350px" type="submit" class="btn" ><@spring.message 'profile.adminPanel'/></button>
                                </div>
                            </form>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">
                        <h1>${user.username}</h1>
                        <p>${user.email}</p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <table style="width: 100%; border-collapse: collapse;" border="1">
                            <tbody>
                            <#if authHistory?has_content>
                                <div class="container">
                                    <table class="minimalistBlack">
                                        <thead>
                                        <th><@spring.message 'profile.date'/></th>
                                        <th><@spring.message 'profile.time'/></th>
                                        <th><@spring.message 'profile.ip'/></th>
                                        </thead>
                                        <#list authHistory as row>
                                            <tr>
                                                <td>${row.toDateTimeString("MMMM dd, yyyy")}</td>
                                                <td>${row.toDateTimeString("HH:mm")}</td>
                                                <td>${row.ip}</td>
                                            </tr>
                                        </#list>
                                    </table>
                                </div>
                            </#if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
    <tbody>
    <#if avatarHistory?has_content>
        <div class="container">
            <table class="minimalistBlack">
                <thead>
                <th><@spring.message 'profile.file'/></th>
                <th><@spring.message 'profile.size'/></th>
                <th><@spring.message 'profile.mime'/></th>
                </thead>
                <#list avatarHistory as row>
                    <tr>
                        <td> <a href="../../images/avatar/${row.fileNameUUID}"  target="_blank">${row.fileName}</a></td>
                        <td>${row.size}</td>
                        <td>${row.mime}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    </tbody>
</table>
<@ui.tail/>