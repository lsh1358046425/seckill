local stockKey = KEYS[1]
local goodsNum = redis.call('get',stockKey)
if goodsNum > 0
then redis.call('decr',stockKey)
    return '1'
else
    return '0'
end