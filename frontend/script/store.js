import {createStore} from 'vuex'

// Create a new store instance.
const store = createStore({
    state() {
        return {
            user: null,
            accessJwt: null,
            refreshJwt: null,
            channels: [],
            selectedChannel: null,
            messages: [],
            friendList: [],
            isLogin: false
        }
    },
    mutations: { //state값을 변화시키는 로직
        setUser(state, user) {
            state.user = user;
        },
        setAccessJwt(state, jwt) {
            state.accessJwt = jwt;
        },
        setRefreshJwt(state, jwt) {
            state.refreshJwt = jwt;
        },
        setChannels(state, channels) {
            state.channels = channels;
        },
        setSelectedChannel(state, channel) {
            state.selectedChannel = channel;
        },
        setMessages(state, messages) {
            state.messages = messages;
        },
        addMessage(state, message) {
            state.messages.push(message);
        },
        setFriendList(state, friends) {
            state.friendList = friends;
        },
        setIsLogin(state, status){
            state.isLogin = state;
        }
    },
    actions: { //비즈니스 로직
        login({commit}, user) {
            // 로그인 로직
            commit('setUser', user);
        },
        logout({commit}) {
            // 로그아웃 로직
            commit('setUser', null);
            commit('setAccessJwt', null);
            commit('setRefreshJwt', null);
        },
        fetchChannels({commit}) {
            // 채널 목록 조회 로직
            const channels = [
                {id: 1, name: 'Channel 1'},
                {id: 2, name: 'Channel 2'},
            ];
            commit('setChannels', channels);
        },
        selectChannel({commit}, channel) {
            // 채널 선택 로직
            commit('setSelectedChannel', channel);
        },
        fetchMessages({commit}) {
            // 메시지 조회 로직
            const messages = [
                {id: 1, content: 'Hello'},
                {id: 2, content: 'Hi'},
            ];
            commit('setMessages', messages);
        },
        fetchFriendList({commit}) {
            // 친구 목록 조회 로직
            const friends = [
                {id: 1, username: 'Alice'},
                {id: 2, username: 'Bob'},
            ];
            commit('setFriendList', friends);
        },
    },
    getters: {
        isAuthenticated(state) {
            return state.refreshJwt !== null;
        },
        isLogin(state){
            return state.isLogin
        }
    },

});

export default store