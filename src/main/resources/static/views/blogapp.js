import postArticleComponent from "../components/postArticleComponent.js";
import articleBox from "../components/articleBox.js";
import themeBoxComponent from "../components/themeBoxComponent.js";
import themeList from "../components/themeList.js";

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