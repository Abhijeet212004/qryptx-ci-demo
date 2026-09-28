"""Settlement file handling."""

from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import padding, rsa
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes


def batch_checksum(batch: bytes) -> bytes:
    """Checksum for the nightly settlement batch."""
    digest = hashes.Hash(hashes.MD5())
    digest.update(batch)
    return digest.finalize()


def archival_key():
    """Key pair for the seven-year settlement archive."""
    return rsa.generate_private_key(public_exponent=65537, key_size=1024)


def wrap_key(public_key, data_key: bytes) -> bytes:
    return public_key.encrypt(data_key, padding.PKCS1v15())


def encrypt_batch(key: bytes, iv: bytes, batch: bytes) -> bytes:
    cipher = Cipher(algorithms.TripleDES(key), modes.CBC(iv))
    encryptor = cipher.encryptor()
    return encryptor.update(batch) + encryptor.finalize()
