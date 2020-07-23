import sendMessageComponent from "../components/sendMessageComponent.js";
import messageBox from "../components/messageBox.js";
import channelBoxComponent from "../components/channelBoxComponent.js";
import channelList from "../components/channelList.js";

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
            <themelList/>
        </div>
    `,
};