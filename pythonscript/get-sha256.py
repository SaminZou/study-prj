import hashlib

password="samin123"
hashed = hashlib.sha256(password.encode()).hexdigest()
print(f"samin:$sha256${hashed}")