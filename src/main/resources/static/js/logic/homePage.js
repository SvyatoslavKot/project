
const authorizationCoockie = getCookie('Authorization').toString();
const state = {
    userGetResponse: {
        name: '',
        lastName: '',
        mail: '',
        gender: '',
        age: ''
    }
}
const logoutButton = document.querySelector('#logout');

window.onload = requestCurrentUser;


function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function requestCurrentUser() {
    requester('http://127.0.0.1:8082/api/v1/users/user')
        .then((body) => responseJsonToState(body))
        .then(renderItem)
        .catch((err) => {
            console.log(err);
        });
}

const requester = async  (url) => {
    const res = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization' : authorizationCoockie
        }
    });
    if (!res.ok) {
        throw  new Error (`Невалидный токен`);
    }
    return await JSON.parse( await res.text());
};

function responseJsonToState(json) {
    state.userGetResponse.name = json.name;
    state.userGetResponse.lastName = json.lastName;
    state.userGetResponse.mail = json.mail;
    state.userGetResponse.gender = json.gender;
    state.userGetResponse.age = json.age;
}
function  renderItem() {
    document.querySelector('#nav-bar-fio').innerHTML =  state.userGetResponse.name + " " + state.userGetResponse.lastName;
    document.querySelector('#slider-user-fio').innerHTML =  state.userGetResponse.name + " " + state.userGetResponse.lastName;
}

logoutButton.addEventListener( "click" , () => logout());

function logout() {
    document.cookie = "Authorization= ; max-age=0";

    window.location.href = 'http://localhost/'
    console.log("log")
}
