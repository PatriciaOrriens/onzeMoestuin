<script id="newMessageTemplate" type="text/x-handlebars-template">
   <!-- <table class="table table-striped new-message">
        <tr>
            <td class="msg-header"><h5>{{sender.username}}</h5><small>{{formattedDateTime}}</small> </td>
            <td class="msg-body">{{messageBody}}</td>
        </tr>
    </table> -->

    <div class="message new-message">
        <strong>{{sender.username}}</strong> {{messageBody}}
        <br />
        <small>{{formattedDateTime}}</small>
    </div>
</script>