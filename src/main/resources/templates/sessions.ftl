<#import "ui.ftl" as ui/>
<@ui.headerWithjQuery title="Welcome to Cinema"/>
<form action="">
    <div class="container">
        <input type="text" id="search" name="filmName" placeholder="Enter a film name"/>
    </div>
</form>
<div id="sessions-search-result"></div>

<script>
    $(document).ready(function () {
        $('#search').keyup(function () {
            $('#sessions-search-result').html('');
            var searchField = $('#search').val();
            if (searchField !== '') {
                $.ajax({
                    url: "sessions/search",
                    method: "GET",
                    data: {filmName: searchField},
                    dataType: "JSON",
                    success: function (data) {
                        var len = data.sessions.length;
                        for (var i = 0; i < len; i++) {
                            $('#sessions-search-result').append(
                                '<div class="searchItem">'
                                + '<img src="/images/'
                                + data.sessions[i].movie.image.fileNameUUID
                                + '" width="150" height="225"/>'
                                + '<div>'
                                + data.sessions[i].dateTime
                                + '</div>'
                                + '<div>'
                                + '<a href="/sessions/'
                                + data.sessions[i].id
                                + '" target="_blank">'
                                + data.sessions[i].movie.title
                                + '</a>'
                                + '</di>'
                                + '</div>'
                            );
                        }
                    }
                })
            }
        });
    });
</script>
<@ui.tail/>
