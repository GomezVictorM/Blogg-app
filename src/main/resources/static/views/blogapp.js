import sendMessageComponent from "../components/postArticleComponent.js";
import messageBox from "../components/articleBox.js";
import channelBoxComponent from "../components/themeBoxComponent.js";
import channelList from "../components/themeList.js";

export default {
	components: {
		postArticleComponent,
		articleBox,
		themeBoxComponent,
		themeList,
	},
	template: `
        <div class="blog-app-cover-div">
            <themeBoxComponent/>
            <div class="article-component-div">
                <articleBox/>
                <postArticleComponent/>
            </div>
            <themeList/>
        </div>
    `,
};