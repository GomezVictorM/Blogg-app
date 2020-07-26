import Vue from "https://cdn.jsdelivr.net/npm/vue@2.6.11/dist/vue.esm.browser.js";
import Vuex from "https://cdn.jsdelivr.net/npm/vuex@3.1.2/dist/vuex.esm.browser.js";
Vue.use(Vuex);

export const store = new Vuex.Store({
	state: {
		currentUser: null,
		currentTheme: null,
		currentThemeArticles: null,
		otherThemes: null,
	},
	mutations: {
		setCurrentUser(state, user) {
			state.currentUser = user;
		},
		setCurrentTheme(state, theme) {
			state.currentTheme = theme;
		},
		setCurrentThemeArticle(state, articles) {
			state.currentThemeArticles = articles;
		},
		setOtherThemes(state, themes) {
			state.setOtherThemes = themes;
		},
		removeThemeFromUser(state, index) {
			state.currentUser.listOfThemes.splice(index, 1);
    },
    addToCurrentThemeArticles(state, articles) {
      console.log(state.currentThemeArticles)
      state.currentThemeArticles.push(article)
      console.log(state.currentThemeArticles)
    },
	},
	actions: {},
});