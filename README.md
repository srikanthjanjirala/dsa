# threads
1. You hit  /api/threads/consume
   ↓
2. Consumer thread runs
   ↓
3. dataReady = false  →  lock.wait()  →  Thread SLEEPS 😴
   ↓
4. You hit  /api/threads/produce  (from another tab)
   ↓
5. Producer sets dataReady = true
   ↓
6. lock.notify()  →  Consumer thread WAKES UP 🔔
   ↓
7. Consumer processes the data ✅