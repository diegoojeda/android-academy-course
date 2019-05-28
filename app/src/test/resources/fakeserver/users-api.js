const usersList = require("./users-list.json")
const singleUser = require("./single-user.json")

module.exports = [
{
    path: '/users/',
    method: 'GET',
    status: (req, res, next) => {
        res.status(200)
        next()
    },
    template: (params, query, body) => {
        return usersList
    }
},
{
    path: '/users/:userId',
    method: 'GET',
    status: (req, res, next) => {
        res.status(200)
        next()
    },
    template: (params, query, body) => {
        singleUser['id'] = params.userId
        return singleUser
    }
}
]