export default {
	template: `
		<div style="padding: 15px;">
            <div class="w3-container w3-padding">
		<h3>Other Channels:</h3>

			<div v-for="(otherTheme, i) in otherThemes" :key="otherTheme.id" class="theme-name-div">
				<h4>{{otherChannel.title}}</h4>
				<button @click="onClickJoinTheme(i)" class="join-button">Join</button>
			</div>
		</div>
    `,
	computed: {
		otherChannels() {
			if (this.$store.state.currentUser === null) {
				return {};
			} else {
				return this.$store.state.currentUser.otherThemes;
			}
		}
	},
	methods: {
		async onClickJoinTheme(i) {
            let userThemeRelation = {
                userId: this.$store.state.currentUser.id,
                channelId: this.$store.state.currentUser.otherThemes[i].id
            }
            let response = await fetch('/rest/relation', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userThemeRelation)
            })
        }
	}
};