!function (a, b, c) {
    "use strict";
    function d(a) {
        return null != a && "" !== a && "hasOwnProperty" !== a && h.test("." + a)
    }

    function e(a, b) {
        if (!d(b))throw g("badmember", 'Dotted member path "@{0}" is invalid.', b);
        for (var e = b.split("."), f = 0, h = e.length; h > f && a !== c; f++) {
            var i = e[f];
            a = null !== a ? a[i] : c
        }
        return a
    }

    function f(a, c) {
        c = c || {}, b.forEach(c, function (a, b) {
            delete c[b]
        });
        for (var d in a)!a.hasOwnProperty(d) || "$" === d.charAt(0) && "$" === d.charAt(1) || (c[d] = a[d]);
        return c
    }

    var g = b.$$minErr("$resource"), h = /^(\.[a-zA-Z_$][0-9a-zA-Z_$]*)+$/;
    b.module("ngResource", ["ng"]).provider("$resource", function () {
        var a = this;
        this.defaults = {
            stripTrailingSlashes: !0,
            actions: {
                get: {method: "GET"},
                save: {method: "POST"},
                query: {method: "GET", isArray: !0},
                remove: {method: "DELETE"},
                "delete": {method: "DELETE"}
            }
        }, this.$get = ["$http", "$q", function (d, h) {
            function i(a) {
                return j(a, !0).replace(/%26/gi, "&").replace(/%3D/gi, "=").replace(/%2B/gi, "+")
            }

            function j(a, b) {
                return encodeURIComponent(a).replace(/%40/gi, "@").replace(/%3A/gi, ":").replace(/%24/g, "$").replace(/%2C/gi, ",").replace(/%20/g, b ? "%20" : "+")
            }

            function k(b, c) {
                this.template = b, this.defaults = o({}, a.defaults, c), this.urlParams = {}
            }

            function l(i, j, r, s) {
                function t(a, b) {
                    var c = {};
                    return b = o({}, j, b), n(b, function (b, d) {
                        q(b) && (b = b()), c[d] = b && b.charAt && "@" == b.charAt(0) ? e(a, b.substr(1)) : b
                    }), c
                }

                function u(a) {
                    return a.resource
                }

                function v(a) {
                    f(a || {}, this)
                }

                var w = new k(i, s);
                return r = o({}, a.defaults.actions, r), v.prototype.toJSON = function () {
                    var a = o({}, this);
                    return delete a.$promise, delete a.$resolved, a
                }, n(r, function (a, e) {
                    var i = /^(POST|PUT|PATCH)$/i.test(a.method);
                    v[e] = function (j, k, l, r) {
                        var s, x, y, z = {};
                        switch (arguments.length) {
                            case 4:
                                y = r, x = l;
                            case 3:
                            case 2:
                                if (!q(k)) {
                                    z = j, s = k, x = l;
                                    break
                                }
                                if (q(j)) {
                                    x = j, y = k;
                                    break
                                }
                                x = k, y = l;
                            case 1:
                                q(j) ? x = j : i ? s = j : z = j;
                                break;
                            case 0:
                                break;
                            default:
                                throw g("badargs", "Expected up to 4 arguments [params, data, success, error], got {0} arguments", arguments.length)
                        }
                        var A = this instanceof v, B = A ? s : a.isArray ? [] : new v(s), C = {}, D = a.interceptor && a.interceptor.response || u, E = a.interceptor && a.interceptor.responseError || c;
                        n(a, function (a, b) {
                            "params" != b && "isArray" != b && "interceptor" != b && (C[b] = p(a))
                        }), i && (C.data = s), w.setUrlParams(C, o({}, t(s, a.params || {}), z), a.url);
                        var F = d(C).then(function (c) {
                            var d = c.data, h = B.$promise;
                            if (d) {
                                if (b.isArray(d) !== !!a.isArray)throw g("badcfg", "Error in resource configuration for action `{0}`. Expected response to contain an {1} but got an {2}", e, a.isArray ? "array" : "object", b.isArray(d) ? "array" : "object");
                                a.isArray ? (B.length = 0, n(d, function (a) {
                                    B.push("object" == typeof a ? new v(a) : a)
                                })) : (f(d, B), B.$promise = h)
                            }
                            return B.$resolved = !0, c.resource = B, c
                        }, function (a) {
                            return B.$resolved = !0, (y || m)(a), h.reject(a)
                        });
                        return F = F.then(function (a) {
                            var b = D(a);
                            return (x || m)(b, a.headers), b
                        }, E), A ? F : (B.$promise = F, B.$resolved = !1, B)
                    }, v.prototype["$" + e] = function (a, b, c) {
                        q(a) && (c = b, b = a, a = {});
                        var d = v[e].call(this, a, this, b, c);
                        return d.$promise || d
                    }
                }), v.bind = function (a) {
                    return l(i, o({}, j, a), r)
                }, v
            }

            var m = b.noop, n = b.forEach, o = b.extend, p = b.copy, q = b.isFunction;
            return k.prototype = {
                setUrlParams: function (a, c, d) {
                    var e, f, h = this, j = d || h.template, k = h.urlParams = {};
                    n(j.split(/\W/), function (a) {
                        if ("hasOwnProperty" === a)throw g("badname", "hasOwnProperty is not a valid parameter name.");
                        !new RegExp("^\\d+$").test(a) && a && new RegExp("(^|[^\\\\]):" + a + "(\\W|$)").test(j) && (k[a] = !0)
                    }), j = j.replace(/\\:/g, ":"), c = c || {}, n(h.urlParams, function (a, d) {
                        e = c.hasOwnProperty(d) ? c[d] : h.defaults[d], b.isDefined(e) && null !== e ? (f = i(e), j = j.replace(new RegExp(":" + d + "(\\W|$)", "g"), function (a, b) {
                            return f + b
                        })):j= j.replace(new RegExp("(/?):" + d + "(\\W|$)", "g"), function (a, b, c) {
                            return "/" == c.charAt(0) ? c : b + c
                        })
                    }), h.defaults.stripTrailingSlashes && (j = j.replace(/\/+$/, "") || "/"), j = j.replace(/\/\.(?=\w+($|\?))/, "."), a.url = j.replace(/\/\\\./, "/."), n(c, function (b, c) {
                        h.urlParams[c] || (a.params = a.params || {}, a.params[c] = b)
                    })
                }
            }, l
        }]
    })
}(window, window.angular);