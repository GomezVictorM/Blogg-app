import createChannel from './createTheme.js'

export default {
    components: {
        createTheme
    },
    template: `
        <div class="theme-box-div">
            <h2>Themes</h2>
            <div v-for="(userTheme, i) in userThemes" :key="userTheme.id" class="theme-name-div">
                <h3 @click="onClick(i)">{{userTheme.title}}</h3>
                <button @click="onClickLeaveTheme(i)" class="delete-button">🗑️</button>
            </div>
            <createTheme/>
        </div>
    `,
    computed: {
        userThemes() {
            if(this.$store.state.currentUser === null) {
                return {}
            } else {
                return this.$store.state.currentUser.listOfThemes
            }
        },
        showUserName(){
            if(this.$store.state.currentUser === null) {
               return ''
            } else {
                return this.$store.state.currentUser.username
            }
        }
    },
    methods: {
        async onClick(i) {
            this.$store.commit('setCurrentTheme', this.$store.state.currentUser.listOfThemes[i])
            let channelId = this.$store.state.currentChannel.id
            let response = await fetch('/rest/articles/' + themeId)
            response = await response.json()
            this.$store.commit('setCurrentThemeArticles', response)
        },
        async onClickLeaveTheme(i) {
            let userThemeRelation = {
                userId: this.$store.state.currentUser.id,
                channelId: this.$store.state.currentUser.listOfThemes[i].id
            }
            console.log(userThemeRelation.userId)
            console.log(userThemeRelation.themeId)
            let response = await fetch('/rest/relation', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userThemeRelation)
            })

            this.$store.commit('removeThemeFromUser', i)
        }
    }
}