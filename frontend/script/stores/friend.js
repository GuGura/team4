import {defineStore} from "pinia";
import {computed, reactive} from "vue";
import api from "../token/axios";


//id 고유아이디
//state 상태 (현재코드에선 초기상태 0으로 리턴해줌)
export const useFriendStore = defineStore("friendStore", () => {

    let user = reactive({
        id: '',
        email: '',
        username: '',
        role: '',
        join_date: '',
        user_icon_url: '',
        user_description: ''
    })
    let friendList = reactive([])
    let searchUsers = reactive([])
    let getSearchUsers = ()=>(computed(()=>{
        return searchUsers
    }))
    let getFriendList = ()=>(computed(()=>{
        return friendList
    }))
    function initFriendList(){
        api.get('/myInfo/friendList')
            .then(({data})=>{
                data.forEach(member =>{
                    if(member.user_ICON_URL !== null&& member.user_ICON_URL !== ''){
                        member.user_ICON_URL = "data:image/png;base64,"+member.user_ICON_URL
                    }else {
                        member.user_ICON_URL = "data:image/png;base64,null"
                    }
                    friendList.push(member)
                })
            })
            .catch(err=>{
                console.log(err)
            })
    }
    function updateFriendInfo() {
        localStorage.removeItem('friend')
        api.post('/myInfo/friend',{friendId:user.id})
            .then(({data}) => {
                const userInfo = data.result
                user.id = userInfo.id
                user.email = userInfo.email
                user.username = userInfo.username
                user.role = userInfo.role
                user.join_date = userInfo.join_date
                if(userInfo.user_icon_url?.trim()){
                    user.user_icon_url = "data:image/png;base64,"+userInfo.user_icon_url
                }else {
                    user.user_icon_url = "data:image/png;base64,null"
                }
                if(userInfo.user_description?.trim()){
                    user.user_description = userInfo.user_description
                }else {
                    user.user_description = "환영합니다. 나만의 메신저를 꾸며보세요!"
                }
                localStorage.setItem('friend',JSON.stringify(user))
            })
    }

    function init(){
        if(localStorage.getItem('friend')?.trim()) {
            user.id = JSON.parse(localStorage.getItem('friend')).id
            user.username = JSON.parse(localStorage.getItem('friend')).username
            user.user_icon_url = JSON.parse(localStorage.getItem('friend')).user_icon_url
            user.role = JSON.parse(localStorage.getItem('friend')).role
            user.email = JSON.parse(localStorage.getItem('friend')).email
            user.user_description = JSON.parse(localStorage.getItem('friend')).user_description
            user.join_date = JSON.parse(localStorage.getItem('friend')).join_date
        }
    }


    return {
        user,
        friendList,
        searchUsers,
        getSearchUsers,
        getFriendList,
        initFriendList,
        updateFriendInfo,
        init
    }
});