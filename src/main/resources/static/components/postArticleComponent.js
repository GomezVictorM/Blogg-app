export default {
    template: `

        <form @submit.prevent="postNewArticle" class="post-article-form">
            <textarea v-model="article" class="post-article-textarea" placeholder="Post a new article..."></textarea>
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
                read: false, //Remove in database, backend and here later if no time left.
                senderId: this.$store.state.currentUser.id, // Fixed?
                themeId: this.$store.state.currentTheme.id, // Fixed?
                receiverId: null, //fix
                direct: false // Change later? remove?
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
            //Collects all timestamps in an array
            let newDateTimeFormat = [
                newArticleDate.getFullYear()+'',
                newArticleDate.getMonth()+1+'',
                newArticleDate.getDate()+'',
                newArticleDate.getHours()+'',
                newArticleDate.getMinutes()+'',
                newArticleDate.getSeconds()+''
            ]
            let dateTime = '';
            //Builds the right time format and add 0 to single digit numbers
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