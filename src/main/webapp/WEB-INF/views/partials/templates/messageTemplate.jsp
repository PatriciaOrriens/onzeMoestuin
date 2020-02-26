<script id="messageTemplate" type="text/x-handlebars-template">
    <table class="table table-striped">
    {{#each this}}
        <tr>
            <td class="msg-header"><h5>{{sender.username}}</h5><small>{{formattedDateTime}}</small> </td>
            <td class="msg-body">{{messageBody}}</td>
        </tr>
    {{/each}}
    </table>
</script>