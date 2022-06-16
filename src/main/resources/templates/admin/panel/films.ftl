<#ftl encoding="utf-8">
<#import "../../ui.ftl" as ui/>
<@ui.header title="🎞 Films panel"/>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<form method="post" action="films" enctype="multipart/form-data" accept-charset="utf-8">
    <div class="container">
        <table>
            <tr>
                <input name="posterFile" placeholder="Upload poster image" type="file" accept="image/*"
                       required/></td>
            </tr>
            <tr>
                <td><input name="title" placeholder="Enter title" type="text" required/></td>

            </tr>
            <tr>

                <td><input name="description" placeholder="Enter description" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="yearOfRelease" placeholder="Enter age year of release" type="text"
                           required/>
            </tr>
            <tr>
                <td><input name="ageRestrictions" placeholder="Enter age restrictions" type="text"
                           required/></td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Add film</button>
                </td>
            </tr>
        </table>
    </div>
</form>

<#if movieList?has_content>
    <div class="container">
        <table class="minimalistBlack">
            <thead>
            <th>id</th>
            <th>Poster</th>
            <th>Title</th>
            <th>Description</th>
            <th>Age Restriction</th>
            <th>Year Of Release</th>
            </thead>
            <#list movieList as row>
                <tr>
                    <td>${row.id}</td>
                    <td>
                        <img src="../../images/${row.poster.fileNameUUID}" width="150" />
                    </td>
                    <td>${row.title}</td>
                    <td>${row.description}</td>
                    <td>${row.ageRestrictions}</td>
                    <td>${row.yearOfRelease?replace(",","")}</td>
                </tr>
            </#list>
        </table>
    </div>
</#if>
<@ui.tail/>