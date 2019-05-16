const usersList = require("./users-list.json")

module.exports = [{
    path: '/users/',
    method: 'GET',
    status: (req, res, next) => {
        res.status(200)
        next()
    },
    template: (params, query, body) => {
        return usersList
    }
}]