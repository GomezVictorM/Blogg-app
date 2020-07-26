export default {
    template: `
      <div id="app">
        <nav>
          <router-link to="/">Home</router-link>
          <router-link to="/blog_app">Blog App</router-link>
        </nav>

        <main>
          <router-view />
        </main>
      </div>
    `,
    async created() {
      let user = await fetch('/auth/whoAmI')

      try {
        user = await user.json()
        this.$store.commit('setCurrentUser', user)
      } catch {}
    }
  }