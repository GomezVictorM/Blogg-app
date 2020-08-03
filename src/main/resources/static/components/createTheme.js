export default {
	template: `
      <div>
        <form @submit.prevent="createNewTheme" class="create-new-theme">
            <input v-model="title" type="text"
            required
            placeholder = "Name your new Theme:">

            <button class="createButton">Create</button>

        </form>
       </div>
    `,

	props: [],
	data() {
		return {
			title: ""
		};
	},

	methods: {
		async createNewTheme() {
			if (this.title != "") {
				let newTheme = {
					title: this.title,
					admin_id: this.$store.state.currentUser.id
				};

				try {
					let theme = await fetch("/rest/themes", {
						method: "POST",
						headers: {
							"Content-Type": "application/json"
						},
						body: JSON.stringify(newChannel)
					});

					this.name = "";

					// Refresh the theme list
				} catch (e) {
					console.log("Unavailable to post this article, sorry!");
					console.log(e);
				}
			}
		}
	}
};