<script id="messageTemplate" type="text/x-handlebars-template">
    {{#each this}}
        <div class="message">
            <strong>{{sender.username}}</strong> {{messageBody}}
            <br />
            <small>{{formattedDateTime}}</small>

        </div>

    {{/each}}
</script>