

const postTitle = document.querySelector('.mail');
const postBody = document.querySelector('.password');
const postButtom = document.querySelector('.singIn');


const state = {
    inputs:[],

    newInputs: {
        inputOne:'',
        inputTwo:''
},

    posts:[],
    newPost: {
        mail: '44',
        password: '44'
    },

    editPost: {

    }
}
postTitle.addEventListener('change', (e) => state.newPost.mail = e.target.value);
postBody.addEventListener('change', (e) => state.newPost.password = e.target.value);


postButtom.addEventListener('click', async () => {
    await logining();
});

const login = async (url) => {
    const res = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(state.newPost),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    });

    if (!res.ok) {
        throw  new Error (`Не верный пароль`)
    }
    const body = await res.headers.get('Authorization');
    return body;
};

function logining() {
    login('http://127.0.0.1:8081/api/v1/auth/login')
        .then((body) => {
           document.cookie = 'Authorization=' + body
           window.location.replace('http://localhost/');
        })
        .catch((err) => {
            console.log(err);
            renderItem(err);

        });
}

function  renderItem(error) {
    const err = error.toString().split('Error: ');
    document.querySelector('#error-title').innerHTML =  err[1] ;

    //
    //$('#error-title').append("<h5> jjj</h5>" );

}








