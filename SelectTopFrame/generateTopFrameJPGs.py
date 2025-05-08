import cv2

topFrames = {
    2: 90,
    3: 85,
    4: 76,
    5: 51,
    6: 108,
    7: 42,
    8: 42,
    9: 97,
    10: 2
}

for i in range(1, 11):
    vid = cv2.VideoCapture("videos\\Vid"+str(i)+"_002.mp4")
    currentFrameNumber = 0
    while True:
        ret,frame = vid.read() 
        if not ret:
            break
        if currentFrameNumber == topFrames.get(i)-1:
            cv2.imwrite("outputFrames\\Vid"+str(i)+"_002_TopFrame.jpg", frame)
        currentFrameNumber += 1

vid.release() 
cv2.destroyAllWindows() 
