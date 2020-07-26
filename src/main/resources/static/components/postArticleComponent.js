export default {
    template: `

        <form @submit.prevent="postNewArticle" class="post-article-form">
            <textarea v-model="article" class="post-article-text area" placeholder="Post a new article..."></textarea>
            <button class="post-article-button">Send</button>
        </form>
    `,
    data() {
        return {
            article: ''
        }
    },
    methods: {
        async postNewArticle() {
            let dateTime = this.getDateTime()

            let newArticle = {
                articleDate: dateTime, //Fixed?
                article: this.article,
                read: false,
                senderId: this.$store.state.currentUser.id,
                themeId: this.$store.state.currentTheme.id,
                receiverId: null, //fix
                direct: false
            }

            let response = await fetch('/rest/articles', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newMessage)
            })

            response = await response.json()
            this.article = ''
        },
        getDateTime() {
            let newArticleDate = new Date();
            //Collects timestamps in an array here
            let newDateTimeFormat = [
                newArticleDate.getFullYear()+'',
                newArticleDate.getMonth()+1+'',
                newArticleDate.getDate()+'',
                newArticleDate.getHours()+'',
                newArticleDate.getMinutes()+'',
                newArticleDate.getSeconds()+''
            ]
            let dateTime = '';
            //Time format here
            for (let i = 0; i < newDateTimeFormat.length; i++) {
                dateTime += newDateTimeFormat[i].length === 1 ? "0" + newDateTimeFormat[i] : newDateTimeFormat[i]
                if(i >= 0 && i < 2) {
                    dateTime += "-"
                } else if(i === 2) {
                    dateTime += " "
                } else if(i > 2 && i < 5) {
                    dateTime += ":"
                }
            }
            return dateTime
        }
    }
}