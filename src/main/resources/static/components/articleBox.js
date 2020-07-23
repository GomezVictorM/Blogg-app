export default {
    template: `
        <div class="article-box-div">
            <h2>Theme: <span style="color: grey;">{{showThemeName}}</span></h2>
            <div v-for="article in showarticles" class="article-div" :hover="true">
                <span class="inner-article-div">
                    <p class="article-name">{{article.senderName}}</p>
                    <p class="article-date">{{article.articleDate}}</p>
                    <button v-if="checkDeleteArticle(article.senderId)" @click="onClick(article.id)" class="delete-button">🗑️</button>
                    <button v-if="checkIfAdmin()" @click="removeUser(article.senderId)" class="remove-user-button">Remove User</button>
                </span>
                <p class="article-p">{{message.message}}</p>
            </div>
        </div>
    `,
    data() {
        return {
            hover: false
        }
    },
    computed: {
        showArticles() {
            if(this.$store.state.currentThemeArticles === null) {
                return {}
            } else {
                return this.$store.state.currentThemeArticles
            }
        },
        showThemeName() {
            if (this.$store.state.currentTheme === null) {
                return 'Public Room'
            } else {
                return this.$store.state.currentTheme.title
            }
        }
    },
    methods: {
        async onClick(id) {
            let response = await fetch('/rest/articles/' + id, {
                method: 'Delete'
            })
        },
        checkDeleteMessage(senderId) {
            if(senderId === this.$store.state.currentUser.id || this.$store.state.currentTheme.admin_id === this.$store.state.currentUser.id) {
                return true
            } else {
                return false
            }
        },
        async removeUser(senderId) {
            let userThemeRelation = {
                userId: senderId,
                channelId: this.$store.state.currentTheme.id
            }
            let response = await fetch('/rest/relation', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userThemeRelation)
            })
        },
        checkIfAdmin() {
            if(this.$store.state.currentTheme.admin_id === this.$store.state.currentUser.id) {
                return true
            } else {
                return false
            }
        }
    }
}