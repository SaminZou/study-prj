import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex);

const state = {
    // 'accessToken': null
    showIndex: true
};

const getters = {
    getShowIndex: state => {
        return state.showIndex;
    }
};

const mutations = {
    changeShowIndex(state, showIndex) {
        state.showIndex = showIndex
    }
};

export default new Vuex.Store({
    state,
    getters,
    mutations
});