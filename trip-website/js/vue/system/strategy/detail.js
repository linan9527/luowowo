var user = getUserInfo();
var vue = new Vue({
    el:"#app",
    data:{
        strategy:{},
        page:{},
        vo:{},
        sids:[]
    },
    methods:{
        strategyThumbup:function(){
            ajaxPost("/strategy/strategyThumbup",{sid:vue.strategy.id}, function (data) {
                var map =  data.data;
                if(map.ret){
                    popup("顶成功啦");
                }else{
                    popup("今天你已经定过了");
                }
                vue.vo = map.vo;
            })
        },
        favor:function(){
            ajaxPost("/strategy/favor",{sid:vue.strategy.id}, function (data) {
                var map =  data.data;
                if(map.ret){
                    popup("收藏成功");
                }else{
                    popup("已取消收藏");
                }
                vue.vo = map.vo;
                vue.sids = map.sids;

            })
        },
        contentFocus:function(){
            $("#content").focus();
        },
        commentThumb:function(commentId){
            ajaxPost("/strategy/commentThumb",{cid:commentId,sid:getParams().id}, function (data) {
                vue.page = data.data;
            })
        },
        mouseover:function(even){
            $(even.currentTarget).find(".rep-del").css("display", "block");
        },
        mouseout:function(even){
            $(even.currentTarget).find(".rep-del").css("display", "none");
        },
        doPage:function (page) {//分页
            ajaxGet("/strategy/commentList", {currentPage:page, strategyId:vue.strategy.id}, function(data){
                vue.page = data.data;
            })
        },
        addComment:function(){ //添加评论
            var param = {}
            param.strategyId = vue.strategy.id;
            param.strategyTitle = vue.strategy.title;

            var content = $("#content").val();
            if(!content){
                popup("评论内容必填");
                return;
            }
            param.content = content;
            $("#content").val('');

            ajaxPost("/strategy/addComment",param, function (data) {
                var map = data.data;
                vue.page = map.page;
                vue.vo =map.vo;
            })
        }
    },
    filters:{
        dateFormat:function(date){
            return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
        }
    },
    mounted:function () {
        var param = getParams();

        //吐司
        ajaxGet("/strategy/detail",{id:param.id}, function (data) {
            var map = data.data;
            vue.strategy = map.strategy;
            vue.page = map.page;

            vue.vo =map.vo;
            vue.sids =map.sids;
            console.log(vue.strategy)
            //分页
            buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
        })
    }
});

